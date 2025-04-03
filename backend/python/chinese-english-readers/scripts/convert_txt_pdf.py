import os
import platform
from util import find_project_root
from fpdf import FPDF


def convert_txt_to_pdf(txt_file_path: str, pdf_file_path: str) -> None:
    pdf = FPDF()
    pdf.add_page()
    os_name = platform.system()
    pdf.set_auto_page_break(auto=True, margin=15)

    if os_name == "Windows":
        font_name = "SimSun"
        font_path = "C:/Windows/Fonts/SimSun.ttc"  # Default path for SimSun on Windows
    elif os_name == "Darwin":  # Darwin is the system name for macOS
        font_name = "HiraginoSansGB"
        font_path = "/System/Library/Fonts/Hiragino Sans GB.ttc"  # Default path for STHeiti Light on macOS
    else:
        raise Exception("Unsupported OS. This code supports Windows and macOS only.")

    pdf.add_font(font_name, "", font_path)
    pdf.set_font(font_name, size=12)

    with open(txt_file_path, 'r', encoding='utf-8') as file:
        for line in file:
            pdf.cell(200, 10, text=line, new_x="LMARGIN", new_y="NEXT")

    pdf.output(pdf_file_path)


if __name__ == "__main__":
    import argparse

    project_root = find_project_root()
    convert_txt_to_pdf(f"{project_root}/note.txt", f"{project_root}/test.pdf")