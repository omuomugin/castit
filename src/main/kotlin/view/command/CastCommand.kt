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

    override fun run() {

        val serializedConfig = Yaml.default.parse(Config.serializer(), config.readText()).let {
            ConfigConverter.convert(it)
        }

        val runner = CastRunner(serializedConfig)

        echo(runner.run())
    }
}