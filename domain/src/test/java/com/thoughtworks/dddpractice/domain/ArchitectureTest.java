package com.thoughtworks.dddpractice.domain;


import com.thoughtworks.dddpractice.framework.annotations.domain.AggregateRoot;
import com.thoughtworks.dddpractice.framework.annotations.domain.DomainEntity;
import com.thoughtworks.dddpractice.framework.annotations.domain.DomainException;
import com.thoughtworks.dddpractice.framework.annotations.domain.DomainFactory;
import com.thoughtworks.dddpractice.framework.annotations.domain.DomainPolicy;
import com.thoughtworks.dddpractice.framework.annotations.domain.DomainPolicyImpl;
import com.thoughtworks.dddpractice.framework.annotations.domain.DomainRepository;
import com.thoughtworks.dddpractice.framework.annotations.domain.DomainService;
import com.thoughtworks.dddpractice.framework.annotations.domain.ValueObject;
import com.thoughtworks.dddpractice.framework.annotations.event.DomainEvent;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaConstructor;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import com.tngtech.archunit.lang.syntax.elements.GivenClassesConjunction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.constructors;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

public class ArchitectureTest {

  private static final String DOMAIN = "..domain..";
  private static final String APPLICATION = "..application..";
  private static final String INFRASTRUCTURE = "..infrastructure..";
  private static final String REPRESENTATION = "..representation..";

  static JavaClasses importedClasses;

  @BeforeAll
  public static void setUp() {
    importedClasses = new ClassFileImporter()
      .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
      .importPackages(ArchitectureTest.class.getPackage().getName());
  }

  @Test
  public void domain_module_should_not_depends_on_application_or_infrastructure_module() {
    ArchRule rule = noDomainObjects()
      .should().dependOnClassesThat().resideInAPackage(APPLICATION)
      .orShould().dependOnClassesThat().resideInAPackage(INFRASTRUCTURE)
      .orShould().dependOnClassesThat().resideInAPackage(REPRESENTATION);

    rule.check(importedClasses);
  }

  @Test
  public void aggregate_root_should_not_export_public_constructor_and_only_be_called_by_factory() {

    ArchRule rule = constructors().that()
      .areDeclaredInClassesThat()
      .areAnnotatedWith(AggregateRoot.class)
      .should().bePackagePrivate()
      .andShould(constructorOnlyBeCalledBy(DomainFactory.class));

    rule.check(importedClasses);
  }

  @Test
  public void entity_should_not_export_public_constructor_and_only_be_called_by_aggregate_root_order_builder() {
    ArchRule rule = constructors().that()
      .areDeclaredInClassesThat().areAnnotatedWith(DomainEntity.class)
      .and().areDeclaredInClassesThat().areNotAnnotatedWith(AggregateRoot.class)
      .should().bePackagePrivate()
      .andShould(constructorOnlyBeCalledBy(AggregateRoot.class))
      .orShould(constructorOnlyBeCalledByBuilder());

    rule.check(importedClasses);
  }

  @Test
  public void entity_methods_should_only_be_called_by_aggregate_root_or_factory() {
    ArchRule rule = methods().that()
      .haveNameNotMatching("^get(.*)")
      .and().haveNameNotMatching("^is(.*)")
      .and().areDeclaredInClassesThat().areAnnotatedWith(DomainEntity.class)
      .and().areDeclaredInClassesThat().areNotAnnotatedWith(AggregateRoot.class)
      .should().bePackagePrivate()
      .andShould(methodOnlyBeCalledBy(AggregateRoot.class))
      .orShould(methodOnlyBeCalledBy(DomainFactory.class));

    rule.check(importedClasses);
  }

  @Test
  public void domain_object_should_annotated() {
    ArchRule rule = domainObjects()
      .and().areNotInterfaces()
      .should().beAnnotatedWith(AggregateRoot.class)
      .orShould().beAnnotatedWith(DomainEntity.class)
      .orShould().beAnnotatedWith(ValueObject.class)
      .orShould().beAnnotatedWith(DomainFactory.class)
      .orShould().beAnnotatedWith(DomainPolicyImpl.class)
      .orShould().beAnnotatedWith(DomainException.class)
      .orShould().beAnnotatedWith(DomainEvent.class)
      .orShould().beAnnotatedWith(DomainService.class);

    rule.check(importedClasses);

    rule = domainObjects()
      .and().areInterfaces()
      .should().beAnnotatedWith(DomainRepository.class)
      .orShould().beAnnotatedWith(DomainPolicy.class);

    rule.check(importedClasses);
  }


  private ArchCondition<JavaConstructor> constructorOnlyBeCalledBy(Class<? extends Annotation> annotationType) {
    return new ArchCondition<JavaConstructor>("only be called by " + annotationType.getCanonicalName()) {
      @Override
      public void check(JavaConstructor method, ConditionEvents events) {
        method.getCallsOfSelf().stream()
          .filter(call -> !call.getOriginOwner().isAnnotatedWith(annotationType) && !call.getOriginOwner().equals(call.getTargetOwner()))
          .forEach(call -> events.add(SimpleConditionEvent.violated(method, call.getDescription())));
      }
    };
  }

  private ArchCondition<JavaMethod> methodOnlyBeCalledBy(Class<? extends Annotation> annotationType) {
    return new ArchCondition<JavaMethod>("only be called by " + annotationType.getCanonicalName()) {
      @Override
      public void check(JavaMethod method, ConditionEvents events) {
        method.getCallsOfSelf().stream()
          .filter(call -> !call.getOriginOwner().isAnnotatedWith(annotationType))
          .forEach(call -> events.add(SimpleConditionEvent.violated(method, call.getDescription())));
      }
    };
  }


  private ArchCondition<JavaConstructor> constructorOnlyBeCalledByBuilder() {
    return new ArchCondition<JavaConstructor>("only be called by builder") {
      @Override
      public void check(JavaConstructor method, ConditionEvents events) {
        method.getCallsOfSelf().stream()
          .filter(call -> !(
            call.getOriginOwner().getName().startsWith(call.getOriginOwner().getName())
              && call.getOriginOwner().getName().endsWith("Builder")))
          .forEach(call -> events.add(SimpleConditionEvent.violated(method, call.getDescription())));
      }
    };
  }

  private GivenClassesConjunction domainObjects() {
    return classes().that()
      .resideInAPackage(DOMAIN)
      .and().haveNameNotMatching("(.*)Builder$")
      .and().haveNameNotMatching("(.*)DTO$");
  }

  private GivenClassesConjunction noDomainObjects() {
    return noClasses().that()
      .resideInAPackage(DOMAIN)
      .and().haveNameNotMatching("(.*)Builder$")
      .and().haveNameNotMatching("(.*)DTO$");
  }
}
