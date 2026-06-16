<?php
session_start();
require(__DIR__ . '/../config/db.php');

$error = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {

    $email = trim($_POST['email']);
    $password = trim($_POST['password']);

    $stmt = $conn->prepare("SELECT * FROM users WHERE email = ?");
    $stmt->bind_param("s", $email);
    $stmt->execute();

    $result = $stmt->get_result();
    $user = $result->fetch_assoc();

    if ($user && password_verify($password, $user['password'])) {

        $_SESSION['user_id'] = $user['id'];
        $_SESSION['username'] = $user['username'];
        $_SESSION['is_admin'] = $user['is_admin'];

        if ($user['is_admin'] == 1) {
            header("Location: ../admin/dashboard.php");
        } else {
            header("Location: ../user/dashboard.php");
        }
        exit();

    } else {
        $error = "Invalid email or password!";
    }
}
?>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<style>
body {
    margin:0;
    font-family: 'Segoe UI', sans-serif;
    height:100vh;
    display:flex;
    background: linear-gradient(135deg,#0f172a,#1e293b);
}

/* LEFT SIDE */
.left {
    flex:1;
    background: linear-gradient(135deg,#4f46e5,#22c55e);
    display:flex;
    justify-content:center;
    align-items:center;
    color:white;
    padding:40px;
}

.left h1 {
    font-size:40px;
    margin-bottom:10px;
}

/* RIGHT SIDE */
.right {
    flex:1;
    display:flex;
    justify-content:center;
    align-items:center;
}

/* GLASS CARD */
.card {
    width:350px;
    padding:30px;
    background: rgba(255,255,255,0.08);
    backdrop-filter: blur(12px);
    border-radius:16px;
    box-shadow:0 10px 30px rgba(0,0,0,0.4);
    color:white;
}

h2 {
    text-align:center;
    margin-bottom:20px;
}

input {
    width:100%;
    padding:12px;
    margin:10px 0;
    border:none;
    border-radius:8px;
    outline:none;
    background: rgba(255,255,255,0.15);
    color:white;
}

input::placeholder {
    color:#ddd;
}

button {
    width:100%;
    padding:12px;
    background:#22c55e;
    border:none;
    border-radius:8px;
    font-weight:bold;
    cursor:pointer;
    color:white;
    transition:0.3s;
}

button:hover {
    transform:scale(1.03);
    background:#16a34a;
}

.error {
    background:rgba(239,68,68,0.2);
    padding:10px;
    border-radius:8px;
    text-align:center;
    margin-bottom:10px;
}

a {
    color:#93c5fd;
    text-decoration:none;
}

a:hover {
    text-decoration:underline;
}

.small {
    text-align:center;
    margin-top:15px;
    font-size:13px;
}
</style>
</head>

<body>

<div class="left">
    <div>
        <h1>Appointment System</h1>
        <p>Welcome back! Please login to continue.</p>
    </div>
</div>

<div class="right">

    <div class="card">

        <h2>Login</h2>

        <?php if (!empty($error)) echo "<div class='error'>$error</div>"; ?>

        <form method="POST">
            <input type="email" name="email" placeholder="Email" required>
            <input type="password" name="password" placeholder="Password" required>

            <button type="submit">Sign In</button>
        </form>

        <div class="small">
            No account? <a href="register.php">Create one</a>
        </div>

    </div>

</div>

</body>
</html>