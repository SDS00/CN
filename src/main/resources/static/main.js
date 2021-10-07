async function doTurn() {
    if(!(await (await fetch("game/isFinished")).json())) {
        this.innerHTML = await (await fetch("/game/currentPlayer")).json();

        await fetch("/game/turn", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({row: this.dataset.row, col: this.dataset.col})
        })

        const info = document.querySelector("#info");

        if((await (await fetch("game/isFinished")).json())) {
            info.innerHTML = "Победитель: " + this.innerHTML;
        } else {
            info.innerHTML = "Ход: " + (await (await fetch("/game/currentPlayer")).json());
        }
    }
}

function createGameMap(mapInfo) {
    const container = document.querySelector("#game-map");
    container.innerHTML = "";

    for(let i = 0; i < mapInfo.length; i++) {
        const rowContainer = document.createElement("div");
        rowContainer.classList.add("game_map__row");

        for(let j = 0; j < mapInfo.length; j++) {
            const cellButton = document.createElement("button");
            cellButton.classList.add("game_map__cell");

            cellButton.dataset.row = `${i}`;
            cellButton.dataset.col = `${j}`;

            cellButton.onclick = doTurn;

            rowContainer.append(cellButton);
        }

        container.append(rowContainer);
    }
}

document.querySelector("#new-game").addEventListener("click", async () => {
    await fetch("/game/new", { method: "POST" });

    const mapInfo = await (await fetch("/game/getMapInfo")).json();

    createGameMap(mapInfo);
});

