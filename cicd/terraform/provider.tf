terraform {
  required_providers {
    local = {
      source  = "hashicorp/local"
      version = "~> 2.0" # Update the version to the one compatible with Terraform 1.7.4
    }
  }
  required_version = ">= 1.7.4"
}

provider "local" {
  # No version attribute here as the version is specified in the required_providers block above
}