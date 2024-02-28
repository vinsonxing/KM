/*
 * Top_level definitions
 */
 
//================================================================== Module 1

module "module_1" {
 	source = "./modules/module_1/inner"
  module_1_var_string = "string_variable"
  module_1_var_map_object = {
      "example_1" = {
          name    = "example_1_name"
          content = "Content for the name in example_1"
      },
      "example_2" = {
          name    = "example_2_name"
          content = "Content for the name in example_2"
      }
  }
  module_1_var_map_string = {
    "Environment" = "Development"
    "Project"     = "MyProject"
  }

  module_1_var_set_string = ["set_string_1", "set_string_2", "set_string_3"]
} 
 
//================================================================== Module 2

module "module_2" {
  source = "./modules/module_2"
  module_2_var_string = module.module_1.output_content
} 

