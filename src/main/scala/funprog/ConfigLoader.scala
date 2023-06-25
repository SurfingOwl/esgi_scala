package funprog

import com.typesafe.config.{Config, ConfigFactory}

object ConfigLoader {

  val config: Config = ConfigFactory.load()

  val inputFileUrl: String = config.getString("application.input-file")
  val jsonOutputFile: String = config.getString("application.output-json-file")
//  val csvOutputFile: String = config.getString("application.output-csv-file")
}
