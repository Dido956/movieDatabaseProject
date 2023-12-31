const addActorButton = document.getElementById('addActorButton');
const dynamicActorContainer = document.getElementById('dynamicActorContainer');

addActorButton.style.marginBottom = '10px';

addActorButton.addEventListener('click', () => {
    const newActorField = document.createElement('div');
    newActorField.classList.add('form-floating', 'mb-3');

    const newActorInput = document.createElement('input');
    const inputId = 'actorName' + Date.now();
    newActorInput.setAttribute('type', 'text');
    newActorInput.setAttribute('name', 'actors');
    newActorInput.setAttribute('th:field', '*{actors}');
    newActorInput.setAttribute('placeholder', 'Enter actor name');
    newActorInput.setAttribute('required', '');
    newActorInput.classList.add('form-control');
    newActorInput.setAttribute('id', inputId);

    const newActorLabel = document.createElement('label');
    newActorLabel.setAttribute('for', inputId);
    newActorLabel.textContent = 'Actor Names';

    newActorField.appendChild(newActorInput);
    newActorField.appendChild(newActorLabel);

    const removeActorButton = document.createElement('button');
    removeActorButton.setAttribute('type', 'button');
    removeActorButton.classList.add('btn', 'btn-danger', 'remove-actor-button');
    removeActorButton.textContent = 'Remove';

    removeActorButton.style.marginTop = '10px';
    removeActorButton.style.padding = '5px 10px';

    removeActorButton.addEventListener('click', () => {
        newActorField.parentNode.removeChild(newActorField);
    });

    newActorField.appendChild(removeActorButton);
    dynamicActorContainer.appendChild(newActorField);
});
