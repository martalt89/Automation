$ cat jenkins.sh
# Important note:
#   only develop/release/feature/hotfix branches are allowed to deploy to Artifactory

# Compile project, run tests, deploy to Artifactory(as per above note)
parameters:
  - choice:
      name: Run_File
      choices:
        - patient_web_run.xlsx
        - ops_web_run.xlsx
      description: "Select the run file to be used"
mvn -U clean install exec:java -Dexec.arguments="Run_File=$Run_File"
