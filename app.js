document.getElementById("leaveForm").addEventListener("submit", async (e) => {
  e.preventDefault();
  const form = new FormData(e.target);
  const data = Object.fromEntries(form.entries());

  const res = await fetch(`${CONFIG.API_BASE_URL}/api/leave`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(data)
  });

  const message = await res.text();
  document.getElementById("message").innerText = message;
});
