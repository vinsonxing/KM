resource "local_file" "content_from_output" {
  filename = "${path.cwd}/output/output-copy.txt"
  content = "${var.module_2_var_string} - module 2"
}
