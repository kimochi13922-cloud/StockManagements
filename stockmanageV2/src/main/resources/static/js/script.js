// ==================== SEARCH FUNCTIONALITY ====================
function searchTable() {
    const input = document.querySelector('.search-box input');
    const filter = input.value.toLowerCase();
    const table = document.querySelector('table tbody');
    const rows = table.getElementsByTagName('tr');

    for (let i = 0; i < rows.length; i++) {
        const cells = rows[i].getElementsByTagName('td');
        let match = false;

        for (let j = 0; j < cells.length; j++) {
            if (cells[j]) {
                const textValue = cells[j].textContent || cells[j].innerText;
                if (textValue.toLowerCase().indexOf(filter) > -1) {
                    match = true;
                    break;
                }
            }
        }

        rows[i].style.display = match ? '' : 'none';
    }
}

// ==================== FILTER BY CATEGORY ====================
function filterByCategory(category) {
    const table = document.querySelector('table tbody');
    const rows = table.getElementsByTagName('tr');

    for (let i = 0; i < rows.length; i++) {
        const categoryCell = rows[i].getElementsByTagName('td')[2]; // Category column (adjust index if needed)
        
        if (category === '' || categoryCell.textContent === category) {
            rows[i].style.display = '';
        } else {
            rows[i].style.display = 'none';
        }
    }
}

// ==================== ADD MODAL ====================
function openAddModal() {
    document.getElementById('addModal').style.display = 'block';
}

function closeAddModal() {
    document.getElementById('addModal').style.display = 'none';
}

// ==================== EDIT MODAL ====================
function openEditModal(button) {
    // 1. ดึงค่าจาก Attribute ที่เราเพิ่มไว้ใน HTML
    const id = button.getAttribute('data-id');
    const name = button.getAttribute('data-name');
    const info = button.getAttribute('data-info'); // เปลี่ยนจาก category เป็น info
    const quantity = button.getAttribute('data-quantity');
    const price = button.getAttribute('data-price');

    // 2. อ้างอิงถึง Modal
    const modal = document.getElementById('editModal');
    
    // 3. นำค่าไปใส่ใน Input (เช็คชื่อ name="" ให้ตรงกับใน HTML)
    modal.querySelector('input[name="id"]').value = id;
    modal.querySelector('input[name="name"]').value = name;
    modal.querySelector('input[name="info"]').value = info; // แก้ชื่อตรงนี้ด้วย
    modal.querySelector('input[name="quantity"]').value = quantity;
    modal.querySelector('input[name="price"]').value = price;

    // 4. แสดง Modal
    modal.style.display = 'block';
}

function closeEditModal() {
    document.getElementById('editModal').style.display = 'none';
}

// ==================== REMOVE MODAL ====================
function openRemoveModal(button) {
    const id = button.getAttribute('data-id');

    const modal = document.getElementById('removeModal');
    modal.querySelector('input[name="id"]').value = id;

    modal.style.display = 'block';
}

function closeRemoveModal() {
    document.getElementById('removeModal').style.display = 'none';
}

// ==================== INFO MODAL ====================
function openInfoModal(button) {
    // Get the info attribute
    const id = button.getAttribute('data-id');
    const name = button.getAttribute('data-name');
    const info = button.getAttribute('data-info');
    const price = button.getAttribute('data-price');
    const quantity = button.getAttribute('data-quantity');
    const status = button.getAttribute('data-status');
    const category = button.getAttribute('data-category');
    const taxRate = Math.round(parseFloat(button.getAttribute('data-taxRate')) * 100);

    const modal = document.getElementById('infoModal');
    const infoDetails = document.getElementById('infoDetails');

    // Populate the modal with product details
    infoDetails.innerHTML = `
        <h4>Product Name: ${name}</h4>
        <p>Product ID: ${id}</p>
        <p>Category: ${category}</p>
        <p>Info: ${info}</p>
        <p>Price: ${price} Baht</p>
        <p>Quantity: ${quantity}</p>
        <p>Status: ${status}</p>
        <p>Tax Rate: ${taxRate}%</p>
    `;

    modal.style.display = 'block';
}

function closeInfoModal() {
    document.getElementById('infoModal').style.display = 'none';
}

// ==================== CLOSE MODALS ON OUTSIDE CLICK ====================
window.onclick = function(event) {
    const addModal = document.getElementById('addModal');
    const editModal = document.getElementById('editModal');
    const removeModal = document.getElementById('removeModal');

    if (event.target == addModal) {
        closeAddModal();
    }
    if (event.target == editModal) {
        closeEditModal();
    }
    if (event.target == removeModal) {
        closeRemoveModal();
    }
}