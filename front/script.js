const API_URL = 'http://localhost:8080/places';

window.onload = () => {
    fetchPlaces();
}


async function fetchPlaces() {
    const response = await fetch(API_URL);
    const places = await response.json();
    const tbody = document.querySelector('#placesTable tbody');
    tbody.innerHTML = ''; 

    places.forEach(place => {
        const row = document.createElement('tr'); 
        row.innerHTML = `
            <td>${place.Id}</td>
            <td>${place.name}</td>
            <td>${place.state}</td>
            <td class="actions">
                <button onclick="editPlace(${place.Id})">Editar</button>
                <button onclick="deletePlace(${place.Id})">Deletar</button>
            </td>
        `;
        
        tbody.appendChild(row);
    });
}

document.querySelector('#placeForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const id = document.querySelector('#placeId').value;
    const name = document.querySelector('#name').value;
    const state = document.querySelector('#state').value;

    const place = { id, name, state };

    if (id) {
        await fetch(`${API_URL}/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(place)
        });
    } else {
        await fetch(API_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(place)
        });
    }

    document.querySelector('#placeForm').reset();
    fetchPlaces();
});

async function editPlace(id) {
    try{
        const response = await fetch(`${API_URL}/${id}`);
        if (response.ok) {
            const place = await response.json();

            document.querySelector('#placeId').value = place.id; 
            document.querySelector('#name').value = place.name;
            document.querySelector('#state').value = place.state;
        } else {
            console.error("Erro ao buscar o lugar para edição:", response.statusText);
        }
    }catch(error){
        console.error("Erro ao processar a edição", error);
    }
    
}

async function deletePlace(id) {
    await fetch(`${API_URL}/${id}`, {
        method: 'DELETE'
    });
    fetchPlaces();
}
