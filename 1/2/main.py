import re

decimal_words = {
    "one": "1",
    "two": "2",
    "three": "3",
    "four": "4",
    "five": "5",
    "six": "6",
    "seven": "7",
    "eight": "8",
    "nine": "9",
}

sum_of_calibration_values = 0

for input in open("input.txt", "r").read().splitlines():
    result = re.findall(r"\d", input)

    first = result[0] if len(result) > 0 else None
    first_index = input.find(first) if first is not None else None

    for number_word, number in decimal_words.items():
        found_index = input.find(number_word)

        if found_index == -1:
            continue

        if first is None or found_index < first_index:
            first = number
            first_index = found_index

    last = result[-1] if len(result) > 0 else None
    last_index = input.rfind(last) if last is not None else None

    for number_word, number in decimal_words.items():
        found_index = input.rfind(number_word)

        if found_index == -1:
            continue

        if last is None or found_index > last_index:
            last = number
            last_index = found_index

    sum_of_calibration_values += int(first + last)

print(sum_of_calibration_values)
