# Methods of AI Planning Project

This project provide a easy and developer friendly API for common schedule planning problems. Our idea was to build a
generic planner with easy to access user interface. To achieve this we build a simple choco model from the example to get
a deeper insight in the csp framework. Then we translate this simple model into a relative specific solving domain and
implement a framework for this specific domain. The next step will be the removal of the specific domain and replace this
domain by a more generic domain. Then we load our domain by an XML description that will be replaced by a configurable domain via
user interface. (not implemented yet)

## Project Structure

### IO
Provides a reader / writer for importing and exporting concepts and instances of concept as well as defined constraints.

### Persistence
Provide the possibility for a production ready environment

### REST
Provide the possibility for a production ready environment

### Shell
Provide a easy access to the planning API

### Solver
Core functionality that do the planning job

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