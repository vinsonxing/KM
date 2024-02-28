
locals {
  path_prefix = "${path.cwd}/output"
}

resource "local_file" "files_from_map_values" {
  for_each = var.module_1_var_map_object

  filename = "${local.path_prefix}/${each.value.name}"
  content  = each.value.content
}

resource "local_file" "files_from_map_keys" {
  for_each = var.module_1_var_map_object

  filename = "${local.path_prefix}/${each.key}"
  content  = each.key
}

resource "local_file" "from_set" {
  for_each = var.module_1_var_set_string

  filename = "${local.path_prefix}/${each.value}"
  content  = each.value
}

resource "local_file" "from_string" {
  filename = "${local.path_prefix}/${var.module_1_var_string}"
  content  = var.module_1_var_string
}

resource "local_file" "output_test" {
  filename = "${local.path_prefix}/output.txt"
  content = "the content in output"
}

