package com.msi.brules.dsl

import static org.codehaus.groovy.syntax.Types.*

import org.codehaus.groovy.control.CompilerConfiguration
import org.codehaus.groovy.control.customizers.ImportCustomizer
import org.codehaus.groovy.control.customizers.SecureASTCustomizer

import com.msi.brules.engine.EligibilityRuleBase
import com.msi.brules.engine.ProductionRuleEngineFC;

class ConsoleService {

	def eval(String code) {

		eval(code, [:])
	}

	def eval(String code, Map bindingValues) {

		//assignMetaClasses()
		bindingValues.put("ruleBase", new EligibilityRuleBase())
		def conf = prepareCompilerConf()

		createShell(bindingValues, conf).evaluate code

		ProductionRuleEngineFC ruleEngine = new ProductionRuleEngineFC(
				bindingValues["ruleBase"], bindingValues["businessObject"]);
		ruleEngine.run();
	}

	def createShell(Map bindingValues, conf) {

		new GroovyShell(new Binding(bindingValues), conf)
	}

	def prepareCompilerConf() {

		def imports = new ImportCustomizer()
		//imports.addStarImport()
		//imports.addStarImport()

		/*
		 def exprClosure = { expr ->
		 return true
		 } as ExpressionChecker
		 */

		//final ImportCustomizer imports = new ImportCustomizer().addStaticStars('java.lang.Math') // add static import of java.lang.Math
		final SecureASTCustomizer secure = new SecureASTCustomizer()
		secure.with {
			closuresAllowed = true
			methodDefinitionAllowed = true

			//expressionCheckers = [exprClosure]

			importsWhitelist = []
			staticImportsWhitelist = []
			staticStarImportsWhitelist = []
			tokensWhitelist = [
				PLUS,
				MINUS,
				MULTIPLY,
				DIVIDE,
				MOD,
				POWER,
				PLUS_PLUS,
				MINUS_MINUS,
				COMPARE_EQUAL,
				COMPARE_NOT_EQUAL,
				COMPARE_LESS_THAN,
				COMPARE_LESS_THAN_EQUAL,
				COMPARE_GREATER_THAN,
				COMPARE_GREATER_THAN_EQUAL,
			].asImmutable()

			constantTypesClassesWhiteList = [
				Integer,
				Float,
				Long,
				Double,
				BigDecimal,
				Integer.TYPE,
				Long.TYPE,
				Float.TYPE,
				Double.TYPE
			].asImmutable()

			receiversClassesWhiteList = [
				Math,
				Integer,
				Float,
				Double,
				Long,
				BigDecimal,
				Object
			].asImmutable()
		}

		CompilerConfiguration config = new CompilerConfiguration()
		//config.addCompilationCustomizers(imports)
		config.setScriptBaseClass("com.msi.brules.dsl.RuleDslBuilder")
		return config
	}

}
