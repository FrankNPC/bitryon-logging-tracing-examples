
![Java](https://img.shields.io/badge/Java-1.8-orange?logo=java)
![Maven Central](https://img.shields.io/badge/Maven%20Central-available-blue?logo=apachemaven)
![Terraform](https://img.shields.io/badge/Terraform-purple?logo=Terraform)
![License](https://img.shields.io/badge/License-Apache%202.0-lightgrey?logo=open-source-initiative)

## The logging agent ##

The logging agent is to keep scanning and uploading local log files to bitryon portal server.

To download the executable jar: https://repo1.maven.org/maven2/io/bitryon/bitryon-logging-agent/

After launch the agent, it will scan all log files and upload.

The jar is executable: 
> nohup ./openjdk-8/bin/java "-Dbitryon.logging.agent.server-url=wss://dev-logging-ingest-server.bitryon.io/ -Dbitryon.logging.agent.watch-dirs=./logs -jar bitryon-logging-agent-1.0.1.jar

The file name and host id should be less than 256, not blank and not one of '"<>[]{}@&\

To import logs from other logging files, configure bitryon.logging.log-convertor. It converts to bitryon' style to be searched from portal.

More explains see src/test/resources/application.xml


## Terraform integration ##


```java
terraform {
  required_version = ">= 1.13"
  
  required_providers {
    bitryon = {
      source  = "bitryon-io/bitryon-cloud-provider" 
      version = ">= 1.0.51"
    }
  }
}


provider "bitryon" { 
  api_url         = "http://localhost"    # for your dev/test: https://dev-cloud-api.bitryon.io
                                          # for your      prd: https://prd-cloud-api.bitryon.io
                                          # we may change it more formal in the future
  account_api_key = "" # get it from the account.
}

resource "bitryon_cloud_application" "test" {
  name        = "bitryon-your-service"
  retention   = 12312321             # seconds to keep data alive
  tags        = "xxx,few,wwww,22222" # for search purpose
}

output "bitryon_cloud_application" {
  value = bitryon_cloud_application.test
}
```