const fs = require("fs");

const requirements = {
	red: 12,
	green: 13,
	blue: 14,
};

let sumOfGameNumbers = 0;

fs.readFileSync("input.txt", "utf-8")
	.split("\r\n")
	.forEach((line) => {
		if (line.trim() === "") return;

		const [gameString, allSetsString] = line.split(":", 2);

		const gameNumber = Number(gameString.substring("Game ".length));

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

		if (
			sets.every((set) =>
				Object.entries(requirements).every(
					([color, number]) => set[color] === undefined || set[color] <= number
				)
			)
		) {
			sumOfGameNumbers += gameNumber;
		}
	});

console.log(sumOfGameNumbers);
