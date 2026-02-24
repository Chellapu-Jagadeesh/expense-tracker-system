let token = localStorage.getItem('token');
const API_URL = "https://expense-tracker-system-atl9.onrender.com/api/expenses";
const AUTH_URL = "https://expense-tracker-system-atl9.onrender.com/api/auth";
if (token) {
    showApp();
    getExpenses();
} else {
    showAuth();
}
function showApp() {
    document.getElementById('auth-card').style.display = 'none';
    document.getElementById('expenses-section').style.display = 'block';
}
function showAuth() {
    document.getElementById('auth-card').style.display = 'block';
    document.getElementById('expenses-section').style.display = 'none';
}
async function registerUser() {
    const username = document.getElementById('username').value.trim();
    const password = document.getElementById('password').value.trim();
    if (!username || !password) return alert("Please enter both username and password.");
    try {
        const res = await fetch(`${AUTH_URL}/register`, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({username, password})
        });
        if (res.ok) {
            alert("Success! Now please login.");
        } else {
            const text = await res.text();
            try {
                const data = JSON.parse(text);
                alert(data.error || data.message || "Error occurred");
            } catch (e) {
                alert(text || "Error occurred");
            }
        }
    } catch (err) {
        alert("Network error during registration.");
    }
}
async function loginUser() {
    const username = document.getElementById('username').value.trim();
    const password = document.getElementById('password').value.trim();
    if (!username || !password) return alert("Please enter both username and password.");
    try {
        const res = await fetch(`${AUTH_URL}/login`, {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({username, password})
        });
        const data = await res.json();
        if (res.ok) {
            token = data.token;
            localStorage.setItem('token', token);
            showApp();
            getExpenses();
        } else {
            alert(data.error || "Login failed");
        }
    } catch (err) {
        alert("Network error during login.");
    }
}
async function getExpenses() {
    try {
        const res = await fetch(API_URL, {
            headers: {'Authorization': `Bearer ${token}`}
        });
        if (res.status === 401 || res.status === 403) return logout();
        const data = await res.json();
        const list = document.getElementById('expense-list');
        let total = 0;
        list.innerHTML = "";
        data.forEach(e => {
            total += e.amount;
            list.innerHTML += `
                <div class="expense-item">
                    <div class="item-info">
                        <strong>${e.title}</strong>
                        <small>${e.category} • ${new Date(e.dateCreated).toLocaleDateString()}</small>
                    </div>
                    <div style="display:flex; align-items:center;">
                        <span style="font-weight:700; margin-right:10px">₹${e.amount.toFixed(2)}</span>
                        <button class="delete-btn" onclick="deleteExpense(${e.id})">🗑️</button>
                    </div>
                </div>
            `;
        });
        document.getElementById('total-amount').innerText = `₹${total.toFixed(2)}`;
    } catch (err) {
        alert("Failed to fetch expenses.");
    }
}
async function addExpense() {
    const title = document.getElementById('title').value.trim();
    const amount = parseFloat(document.getElementById('amount').value);
    const category = document.getElementById('category').value.trim();
    if (!title || isNaN(amount)) return alert("Please fill in all fields correctly.");
    try {
        const res = await fetch(API_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify({title, amount, category})
        });
        if (res.ok) {
            document.getElementById('title').value = '';
            document.getElementById('amount').value = '';
            getExpenses();
        } else {
            alert("Failed to add expense.");
        }
    } catch (err) {
        alert("Network error while adding expense.");
    }
}
async function deleteExpense(id) {
    if (!confirm("Delete this item?")) return;
    try {
        const res = await fetch(`${API_URL}/${id}`, {
            method: 'DELETE',
            headers: {'Authorization': `Bearer ${token}`}
        });
        if (res.ok) {
            getExpenses();
        } else {
            alert("Failed to delete expense.");
        }
    } catch (err) {
        alert("Network error while deleting expense.");
    }
}
function logout() {
    localStorage.removeItem('token');
    document.getElementById('username').value='';
    document.getElementById('password').value='';
    document.getElementById('expense-list').innerHTML='';
    document.getElementById('total-amount').innerHTML='₹0.00';
    token = null;
    showAuth();
}