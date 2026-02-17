const API_URL="https://expense-tracker-system-atl9.onrender.com/api/expenses";

async function getExpenses(){
    const response=await fetch(API_URL);
    const data=await response.json();
    const list=document.getElementById('expense-list');
    list.innerHTML="";
    data.forEach(expense=>{
        list.innerHTML+=`
            <div class="expense-item">
            <span>${expense.title} - $${expense.amount} [${expense.category}]</span>
            <button onclick="deleteExpense(${expense.id})">❌</button>
            </div>
        `;
    });
}
async function addExpense(){
    const title=document.getElementById('title').value;
    const amount=parseFloat(document.getElementById('amount').value);
    const category=document.getElementById('category').value;
    await fetch(API_URL,{
        method:'POST',
        headers:{'Content-Type':'application/json'},
        body:JSON.stringify({title, amount, category})
    });
    getExpenses();
}
async function deleteExpense(id){
    await fetch(`${API_URL}/${id}`,{method: 'DELETE'});
    getExpenses();
}
getExpenses();