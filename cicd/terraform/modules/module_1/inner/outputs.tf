output "output_content" {
  value = local_file.output_test.content
  description = "The content in the output file"
}
