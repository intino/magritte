package siani.tara.compiler;

import org.junit.Before;
import org.junit.Test;
import siani.tara.TaraToStash;

public class TaraCRunnerTest {


	private String home;

	@Before
	public void setUp() throws Exception {
		home = System.getProperty("user.home");
	}

	@Test
	public void tests() {
		TaraToStash.main(new String[]{home + "/workspace/Stash2Tara/TaraCompiler/res_test/Atmosphere.tara", "UTF-8"});
		TaraToStash.main(new String[]{home + "/workspace/Stash2Tara/TaraCompiler/res_test/AverageTemperature.tara", "UTF-8"});
		TaraToStash.main(new String[]{home + "/workspace/Stash2Tara/TaraCompiler/res_test/Consumption.tara", "UTF-8"});
		TaraToStash.main(new String[]{home + "/workspace/Stash2Tara/TaraCompiler/res_test/EfficiencyOptimizationBranch.tara", "UTF-8"});
		TaraToStash.main(new String[]{home + "/workspace/Stash2Tara/TaraCompiler/res_test/Fruit.tara", "UTF-8"});
		TaraToStash.main(new String[]{home + "/workspace/Stash2Tara/TaraCompiler/res_test/FruitConsumption.tara", "UTF-8"});
		TaraToStash.main(new String[]{home + "/workspace/Stash2Tara/TaraCompiler/res_test/FruitWeeklyConsumption.tara", "UTF-8"});
		TaraToStash.main(new String[]{home + "/workspace/Stash2Tara/TaraCompiler/res_test/GermanPopulation.tara", "UTF-8"});
		TaraToStash.main(new String[]{home + "/workspace/Stash2Tara/TaraCompiler/res_test/JulyTemperature.tara", "UTF-8"});
		TaraToStash.main(new String[]{home + "/workspace/Stash2Tara/TaraCompiler/res_test/Logarithmic.tara", "UTF-8"});
		TaraToStash.main(new String[]{home + "/workspace/Stash2Tara/TaraCompiler/res_test/MarketShareByBrowsers (2).tara", "UTF-8"});
		TaraToStash.main(new String[]{home + "/workspace/Stash2Tara/TaraCompiler/res_test/MarketShareByBrowsers.tara", "UTF-8"});
		TaraToStash.main(new String[]{home + "/workspace/Stash2Tara/TaraCompiler/res_test/Population.tara", "UTF-8"});
		TaraToStash.main(new String[]{home + "/workspace/Stash2Tara/TaraCompiler/res_test/PopulationLargestCity.tara", "UTF-8"});
		TaraToStash.main(new String[]{home + "/workspace/Stash2Tara/TaraCompiler/res_test/Production.tara", "UTF-8"});
		TaraToStash.main(new String[]{home + "/workspace/Stash2Tara/TaraCompiler/res_test/RainFall.tara", "UTF-8"});
		TaraToStash.main(new String[]{home + "/workspace/Stash2Tara/TaraCompiler/res_test/Rate.tara", "UTF-8"});
		TaraToStash.main(new String[]{home + "/workspace/Stash2Tara/TaraCompiler/res_test/SaleStore.tara", "UTF-8"});
		TaraToStash.main(new String[]{home + "/workspace/Stash2Tara/TaraCompiler/res_test/SnowNorway.tara", "UTF-8"});
		TaraToStash.main(new String[]{home + "/workspace/Stash2Tara/TaraCompiler/res_test/Stockpiles.tara", "UTF-8"});
		TaraToStash.main(new String[]{home + "/workspace/Stash2Tara/TaraCompiler/res_test/Temperature.tara", "UTF-8"});
		TaraToStash.main(new String[]{home + "/workspace/Stash2Tara/TaraCompiler/res_test/TemperatureCities.tara", "UTF-8"});
		TaraToStash.main(new String[]{home + "/workspace/Stash2Tara/TaraCompiler/res_test/TotalFruitConsumptionByGender.tara", "UTF-8"});
		TaraToStash.main(new String[]{home + "/workspace/Stash2Tara/TaraCompiler/res_test/WeatherTokyo.tara", "UTF-8"});
		TaraToStash.main(new String[]{home + "/workspace/Stash2Tara/TaraCompiler/res_test/WebVisits.tara", "UTF-8"});
		TaraToStash.main(new String[]{home + "/workspace/Stash2Tara/TaraCompiler/res_test/Wind.tara", "UTF-8"});
		TaraToStash.main(new String[]{home + "/workspace/Stash2Tara/TaraCompiler/res_test/WorldWidePopulationByRegion.tara", "UTF-8"});
		TaraToStash.main(new String[]{home + "/workspace/Stash2Tara/TaraCompiler/res_test/WorlwidePopulation.tara", "UTF-8"});
	}
}
