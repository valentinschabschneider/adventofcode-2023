import re

sum_of_calibration_values = 0

for input in open("input.txt", "r").readlines():
    result = re.findall(r"\d", input)
    sum_of_calibration_values += int(result[0] + result[-1])

print(sum_of_calibration_values)
