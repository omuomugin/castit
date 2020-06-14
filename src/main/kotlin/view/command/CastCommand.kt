package view.command

import com.charleskorn.kaml.Yaml
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.file
import presentation.runner.CastRunner
import view.converter.ConfigConverter
import view.model.Config

class CastCommand : CliktCommand() {

    private val config by option().file(mustExist = true).required()
    private val cast: String by option(help = "cast name").required()

    override fun run() {

        val serializedConfig = Yaml.default.parse(Config.serializer(), config.readText()).let { config ->
            config.casts.firstOrNull {
                it.name == cast
            }?.let {
                ConfigConverter.convert(it, config.token)
            } ?: throw Exception("No name found")
        }

        val runner = CastRunner(serializedConfig)

        echo(runner.run())
    }
}