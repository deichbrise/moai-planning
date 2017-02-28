# Methods of AI Planning Project

This project provides an easy and developer friendly API for common schedule planning problems. Our idea was to build a
generic planner with an easy to access user interface. To achieve this we build a simple choco model from the example to get
a deeper insight into csp framework. Then we translated this simple model into a relative specific solving domain and
implemented a framework for this specific domain. The next step will be the removal of the specific domain and replace this
domain by a more generic domain. Then we load our domain by an XML description that will be replaced by a configurable domain via
user interface. (not implemented yet)

# Authors

Christopher Broecker (chbroecker), Franziska Becker (frabecker), Pascal Stammer (pstammer)

# Algorithm
Entry Point is org.planning.shell.controller.command.impl.SolverCommandsImpl. Here we call org.planning.controller.impl.PlanningControllerImpl and parse the given XML-File .. Then we build the CspSolvingContext and execute the PlanningAlgorithm in org.planning.solver.impl.PlannerImpl. Here we use a the CspModelFactory to construct the model and use the ChocoSolver to solve the CSP. In the CspModelFactory we use a DomainDescriptor and for each constraint type a specific ConstraintDescriptor. Thats all. This approach provide the possibility to easily define your own constraint types and manage your domain easily by xml files which are better readable than Java-Code.

For XML File Structure see: examples/example1.xml

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

## Shell Execution

Simply execute

    java -jar sbin/shell.jar

Than you can execute

    solve $pathToFile

You will find a example file under ./example/example1.xml .

You can execute the example by executing

    solve classpath:/domain/example1.xml

in the shell.

## Developer Info
Make sure you have installed Maven. You can build the project with

    mvn clean install

Then execute

    java -jar shell/target/shell-1.0-SNAPSHOT.jar

After the shell was loaded you can execute

    solve {pathTo ./example/example1.xml}

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
