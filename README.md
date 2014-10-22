# CF example app: ping-pong matching server

This is an app to match ping-pong players with each other. It's currently an
API only, so you have to use `curl` to interact with it.

It has an [acceptance test suite][acceptance-test] you might like to look at.

## Running on [Pivotal Web Services][pws]

Log in.

```bash
cf login -a https://api.run.pivotal.io
```

Target your org / space.

```bash
cf target -o myorg -s myspace
```

Push this app.

```bash
cd app
cf push -n mysubdomain
```

Export the test host

```bash
export HOST=http://mysubdomain.cfapps.io
```

Now follow the [interaction instructions][interaction].

## Running locally

The following assumes you have a working Java 1.8 SDK installed.

Start the application server:

```bash
cd app
./gradlew bootRun
```

Export the test host

```bash
export HOST=http://localhost:8080
```

Now follow the [interaction instructions][interaction].

[acceptance-test]:https://github.com/camelpunch/pong_matcher_acceptance
[pws]:https://run.pivotal.io
[interaction]:https://github.com/camelpunch/pong_matcher_grails#interaction-instructions
