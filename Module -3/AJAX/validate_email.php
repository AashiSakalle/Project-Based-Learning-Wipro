<?php
// SET UP YOUR DATABASE CONNECTION
$host = 'localhost';
$db   = 'your_library_db'; // Change this
$user = 'your_db_user';   // Change this
$pass = 'your_db_password'; // Change this
$charset = 'utf8mb4';

$dsn = "mysql:host=$host;dbname=$db;charset=$charset";
$options = [
    PDO::ATTR_ERRMODE            => PDO::ERRMODE_EXCEPTION,
    PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
    PDO::ATTR_EMULATE_PREPARES   => false,
];

header('Content-Type: application/json'); // Tell client we're sending JSON

try {
     $pdo = new PDO($dsn, $user, $pass, $options);
} catch (\PDOException $e) {
     // Log error: die("Database connection failed: " . $e->getMessage());
     echo json_encode(['exists' => true]); // Fail safe: assume it exists if DB is down
     exit();
}

$response = ['exists' => false];

if (isset($_POST['email'])) {
    $email = trim($_POST['email']);
    
    // Check if email exists in the XYZ_PROFILE table
    $stmt = $pdo->prepare('SELECT COUNT(*) FROM XYZ_PROFILE WHERE Email = ?');
    $stmt->execute([$email]);
    $count = $stmt->fetchColumn();

    if ($count > 0) {
        // Email found, so it already exists
        $response['exists'] = true;
    }
}

echo json_encode($response);
?>