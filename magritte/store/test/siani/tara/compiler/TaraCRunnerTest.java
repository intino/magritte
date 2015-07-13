package siani.tara.compiler;

import org.junit.Before;
import org.junit.Test;
import siani.tara.builder.StashBuilder;

import java.io.File;
import java.nio.charset.Charset;

public class TaraCRunnerTest {


	private String home;

	@Before
	public void setUp() throws Exception {
		home = System.getProperty("user.home");
	}

	@Test
	public void tests() {
		new StashBuilder(new File(home + "/workspace/Stash2Tara/TaraCompiler/res_test/Atmosphere.tara"), Charset.forName("UTF-8")).build();
		new StashBuilder(new File(home + "/workspace/Stash2Tara/TaraCompiler/res_test/AverageTemperature.tara"), Charset.forName("UTF-8")).build();
		new StashBuilder(new File(home + "/workspace/Stash2Tara/TaraCompiler/res_test/Consumption.tara"), Charset.forName("UTF-8")).build();
		new StashBuilder(new File(home + "/workspace/Stash2Tara/TaraCompiler/res_test/EfficiencyOptimizationBranch.tara"), Charset.forName("UTF-8")).build();
		new StashBuilder(new File(home + "/workspace/Stash2Tara/TaraCompiler/res_test/Fruit.tara"), Charset.forName("UTF-8")).build();
		new StashBuilder(new File(home + "/workspace/Stash2Tara/TaraCompiler/res_test/FruitConsumption.tara"), Charset.forName("UTF-8")).build();
		new StashBuilder(new File(home + "/workspace/Stash2Tara/TaraCompiler/res_test/FruitWeeklyConsumption.tara"), Charset.forName("UTF-8")).build();
		new StashBuilder(new File(home + "/workspace/Stash2Tara/TaraCompiler/res_test/GermanPopulation.tara"), Charset.forName("UTF-8")).build();
		new StashBuilder(new File(home + "/workspace/Stash2Tara/TaraCompiler/res_test/JulyTemperature.tara"), Charset.forName("UTF-8")).build();
		new StashBuilder(new File(home + "/workspace/Stash2Tara/TaraCompiler/res_test/Logarithmic.tara"), Charset.forName("UTF-8")).build();
		new StashBuilder(new File(home + "/workspace/Stash2Tara/TaraCompiler/res_test/MarketShareByBrowsers (2).tara"), Charset.forName("UTF-8")).build();
		new StashBuilder(new File(home + "/workspace/Stash2Tara/TaraCompiler/res_test/MarketShareByBrowsers.tara"), Charset.forName("UTF-8")).build();
		new StashBuilder(new File(home + "/workspace/Stash2Tara/TaraCompiler/res_test/Population.tara"), Charset.forName("UTF-8")).build();
		new StashBuilder(new File(home + "/workspace/Stash2Tara/TaraCompiler/res_test/PopulationLargestCity.tara"), Charset.forName("UTF-8")).build();
		new StashBuilder(new File(home + "/workspace/Stash2Tara/TaraCompiler/res_test/Production.tara"), Charset.forName("UTF-8")).build();
		new StashBuilder(new File(home + "/workspace/Stash2Tara/TaraCompiler/res_test/RainFall.tara"), Charset.forName("UTF-8")).build();
		new StashBuilder(new File(home + "/workspace/Stash2Tara/TaraCompiler/res_test/Rate.tara"), Charset.forName("UTF-8")).build();
		new StashBuilder(new File(home + "/workspace/Stash2Tara/TaraCompiler/res_test/SaleStore.tara"), Charset.forName("UTF-8")).build();
		new StashBuilder(new File(home + "/workspace/Stash2Tara/TaraCompiler/res_test/SnowNorway.tara"), Charset.forName("UTF-8")).build();
		new StashBuilder(new File(home + "/workspace/Stash2Tara/TaraCompiler/res_test/Stockpiles.tara"), Charset.forName("UTF-8")).build();
		new StashBuilder(new File(home + "/workspace/Stash2Tara/TaraCompiler/res_test/Temperature.tara"), Charset.forName("UTF-8")).build();
		new StashBuilder(new File(home + "/workspace/Stash2Tara/TaraCompiler/res_test/TemperatureCities.tara"), Charset.forName("UTF-8")).build();
		new StashBuilder(new File(home + "/workspace/Stash2Tara/TaraCompiler/res_test/TotalFruitConsumptionByGender.tara"), Charset.forName("UTF-8")).build();
		new StashBuilder(new File(home + "/workspace/Stash2Tara/TaraCompiler/res_test/WeatherTokyo.tara"), Charset.forName("UTF-8")).build();
		new StashBuilder(new File(home + "/workspace/Stash2Tara/TaraCompiler/res_test/WebVisits.tara"), Charset.forName("UTF-8")).build();
		new StashBuilder(new File(home + "/workspace/Stash2Tara/TaraCompiler/res_test/Wind.tara"), Charset.forName("UTF-8")).build();
		new StashBuilder(new File(home + "/workspace/Stash2Tara/TaraCompiler/res_test/WorldWidePopulationByRegion.tara"), Charset.forName("UTF-8")).build();
		new StashBuilder(new File(home + "/workspace/Stash2Tara/TaraCompiler/res_test/WorlwidePopulation.tara"), Charset.forName("UTF-8")).build();
	}
}
