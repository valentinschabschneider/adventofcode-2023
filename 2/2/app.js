const fs = require("fs");

let sumOfPowers = 0;

fs.readFileSync("input.txt", "utf-8")
	.split("\r\n")
	.forEach((line) => {
		if (line.trim() === "") return;

		const allSetsString = line.split(":", 2)[1];

		const setStrings = allSetsString.split(";");

		const sets = setStrings.map((setString) => {
			const numberColors = setString
				.split(",")
				.map((numberColor) => numberColor.trim());

			return numberColors
				.map((numberColor) => {
					const [number, color] = numberColor.split(" ", 2);

					return {
						number: Number(number),
						color,
					};
				})
				.reduce((setsObject, setObject) => {
					return {
						...setsObject,
						[setObject.color]: setObject.number,
					};
				}, {});
		});

		const maxColorNumbers = {};

		sets.forEach((set) =>
			Object.entries(set).forEach(([color, number]) => {
				if (
					maxColorNumbers[color] === undefined ||
					number > maxColorNumbers[color]
				) {
					maxColorNumbers[color] = number;
				}
			})
		);

		sumOfPowers += Object.values(maxColorNumbers).reduce((a, b) => a * b, 1);
	});

console.log(sumOfPowers);
