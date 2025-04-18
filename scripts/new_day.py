import os
from pathlib import Path

MAIN_DIR = "src/main"
TEST_DIR = "src/test"

def create_input(resources_dir: str, day_number: int):
    file_path = Path(resources_dir) / f"Day{day_number:02d}.txt"
    if file_path.exists():
        return
    file_path.touch()


def generate_test_template(test_dir: str, day_number: int, read_input_func = "readLines"):
    day_number_formatted = f"{day_number:02d}"

    TEST_TEMPLATE = f"""import day{day_number_formatted}.Day{day_number_formatted}
import kotlin.test.Test
import kotlin.test.assertEquals

class Day{day_number_formatted}Test {{
    val input = {read_input_func}("Day{day_number_formatted}")
    val testInput = {read_input_func}("Day{day_number_formatted}", env = "test")

    @Test
    fun testPart1() {{
        assertEquals(0, Day{day_number_formatted}.part1(testInput))
        // assertEquals(0, Day{day_number_formatted}.part1(input))
    }}

    @Test
    fun testPart2() {{
        assertEquals(0, Day{day_number_formatted}.part2(testInput))
        // assertEquals(0, Day{day_number_formatted}.part2(input))
    }}
}}"""

    with open(f"{test_dir}/Day{day_number_formatted}Test.kt", "w") as f:
        f.write(TEST_TEMPLATE)

def generate_solution_template(solution_dir: str, day_number: int, read_input_func = "readLines"):
    day_number_formatted = f"{day_number:02d}"
    input_parameter = f"lines: List<String>" if (read_input_func == "readLines") else "input: String"

    SOLUTION_TEMPLATE = f"""package day{day_number_formatted}
    
import printResult
import {read_input_func}

object Day{day_number_formatted} {{
    fun part1({input_parameter}): Int {{
        return -1
    }}

    fun part2({input_parameter}): Int {{
        return -1
    }}
}}

fun main() {{
    val input = {read_input_func}("Day{day_number_formatted}")
    printResult("1", Day{day_number_formatted}.part1(input))
    // printResult("2", Day{day_number_formatted}.part2(input))
}}
"""

    solution_dir = Path(solution_dir) / f"day{day_number_formatted}"
    os.makedirs(solution_dir, exist_ok=True)

    with open(f"{solution_dir}/Day{day_number:02d}.kt", "w") as f:
        f.write(SOLUTION_TEMPLATE)

if __name__ == '__main__':
    day_number = int(input("Enter the day number (01-25): "))
    assert int(day_number) in range(1, 25)
    read_input_func = input("Enter the read input function (readLines/readInput) (default: readLines): ") or "readLines"
    assert read_input_func in ("readLines", "readInput")

    create_input(Path(MAIN_DIR) / "resources", day_number)
    create_input(Path(TEST_DIR) / "resources", day_number)

    generate_test_template(Path(TEST_DIR) / "kotlin", day_number, read_input_func)
    generate_solution_template(Path(MAIN_DIR) / "kotlin", day_number, read_input_func)