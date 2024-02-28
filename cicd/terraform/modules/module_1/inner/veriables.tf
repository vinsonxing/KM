/*
 * Variables
 */

variable "module_1_var_string" {
    type = string
    description = "The variable with 'string' in module 1"
    default = ""
}

variable "module_1_var_map_object" {
    description = "The variable with 'map' in module 1"
    type        = map(object({
        name    = string
        content = string
    }))

    default = {
        "example_1" = {
            name    = "example_1_name"
            content = "Content for the name in example_1"
        },
        "example_2" = {
            name    = "example_2_name"
            content = "Content for the name in example_2"
        }
    }
}

variable "module_1_var_map_string" {
  description = "A map of environment"
  type        = map(string)
  default     = {
    "Environment" = "Development"
    "Project"     = "MyProject"
  }
}

variable "module_1_var_set_string" {
  description = "A set of string list"
  type        = set(string)
  default     = ["my_app_logs", "my_app_data", "my_app_backups"]
}
