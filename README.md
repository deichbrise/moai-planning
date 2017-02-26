# Methods of AI Planning Project

This project provides an easy and developer friendly API for common schedule planning problems. Our idea was to build a
generic planner with an easy to access user interface. To achieve this we build a simple choco model from the example to get
a deeper insight into csp framework. Then we translated this simple model into a relative specific solving domain and
implemented a framework for this specific domain. The next step will be the removal of the specific domain and replace this
domain by a more generic domain. Then we load our domain by an XML description that will be replaced by a configurable domain via
user interface. (not implemented yet)

## Project Structure

### IO
Provides a reader / writer for importing and exporting concepts and instances of concept as well as defined constraints.

### Persistence
Provides the possibility to have a production ready environment

### REST
Provides the possibility to have a production ready environment

### Shell
Provides an easy access to the planning API

### Solver
Core functionality that does the planning job

### Util
Utility and Helper Functions

## Developer Info
Make sure you have installed Maven. You can build the project with

    mvn clean install

## Architecture


              Shell       REST
                ^          ^
                |          |
                 Controller
                     ^
                     |
                   Solver
                     ^
                     |
               Import / Export
                     ^
                     |
                   Domain
                     ^
                     |
                    Util
