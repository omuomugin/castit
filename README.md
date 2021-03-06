# castit
[![GitHub release](https://img.shields.io/github/v/release/omuomugin/castit)](https://github.com/omuoomugin/castit/releases)

Post Multiple Message to Multiple Channel with single command.

`castit` is a tool to send slack message to multiple channel with only single command. 

Think of situation like working remotely and you belong to multiple teams.
If you need to send `Good Morning! Stated Working!` to multiple team's channel, this tool is for you.

Just write config like below

```yaml
token: "<MY-TOKEN>"
casts:
  - channel: "#teamA"
    content: "good morning"
  - channel: "#teamB"
    content: "Started Working"
  - channel: "#teamC"
    content: "Office Hour Stared. Just ask me anything."
```

and run

```
java -jar castit.jar --config=/path/to/config.yml
```

# Supported Command

```shell
Usage: cast [OPTIONS]

Options:
  --config PATH
  --cast TEXT    cast name
  -h, --help     Show this message and exit
```

# Quick Start
You can install via Homebrew

```bash

$ brew tap omuomugin/tap

$ brew install castit

```

## Getting your token for Slack
TBD

You need to create an app to your slack channel as a user.

see https://api.slack.com/apps

## Writing Yaml

You need a config file written in yaml. (other type will be supported in near future maybe).

```yaml
token: "<MY-TOKEN>"
casts:
  - name: "start"
    messages:
      - channel: "#teamA"
        content: "good morning"
      - channel: "#teamB"
        content: "Started Working"
      - channel: "#teamC"
        content: "Office Hour Stared. Just ask me anything."
```

Ofcourse you can create multiple cast type.
You might want to write another cast type like below.

```yaml
token: MY TOKEN
casts:
  - name: "start"
    messages:
      - channel: "#teamA"
        content: "good morning"
      - channel: "#teamB"
        content: "Started Working"
      - channel: "#teamC"
        content: "Office Hour Stared. Just ask me anything."
  - name: "end"
    messages:
      - channel: "#teamA"
        content: "good night"
      - channel: "#teamB"
        content: "Finished Working. Have a good evening"
      - channel: "#teamC"
        content: "Office Hour Ended. Please talk to me tomorrow."
``` 

## Run and cast (like multi cast) your messages

```shell
castit --config=path/to/your/config.yaml --cast=start
```

# Technical Things

Whole CLI tool is written in Kotlin.

And I want to thank all of the great libraries out there.

- [ajalt/clikt](https://github.com/ajalt/clikt) for basic CLI implementation
- [kittinunf/fuel](https://github.com/kittinunf/fuel) for Http Request
- [charleskorn/kaml](https://github.com/charleskorn/kaml) for serializing, desirializing YAML
  

## How to start it locally

Thanks to [johnrengelman/shadow](https://github.com/johnrengelman/shadow) , it is easy to build fat jar which can use it from command line.

```shell
./gradlew clean shadowjar
```

and you just run jar with command.

```shell
java -jar build/libs/castit-x.y.z-all.jar --help
```