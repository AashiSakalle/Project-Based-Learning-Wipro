<?php
// SET UP YOUR DATABASE CONNECTION (Same as validate_email.php)
$host = 'localhost';
$db   = 'your_library_db';
$user = 'your_db_user';
$pass = 'your_db_password';
$charset = 'utf8mb4';

$dsn = "mysql:host=$host;dbname=$db;charset=$charset";
$options = [
    PDO::ATTR_ERRMODE            => PDO::ERRMODE_EXCEPTION,
    PDO::ATTR_DEFAULT_FETCH_MODE => PDO::FETCH_ASSOC,
    PDO::ATTR_EMULATE_PREPARES   => false,
];

header('Content-Type: application/json');

try {
     $pdo = new PDO($dsn, $user, $pass, $options);
} catch (\PDOException $e) {
     // Log error
     echo json_encode(['success' => false, 'message' => 'DB Connection Error']);
     exit();
}

$response = ['success' => false];

if ($_SERVER['REQUEST_METHOD'] == 'POST' && isset($_POST['email'], $_POST['password'], $_POST['name'])) {
    
    $email = trim($_POST['email']);
    $password = $_POST['password'];
    $name = trim($_POST['name']);
    
    // 1. Basic Server-Side Validation (CRITICAL SECURITY STEP)
    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        echo json_encode(['success' => false, 'message' => 'Invalid email format']);
        exit();
    }
    
    // Check if email already exists one final time (to prevent race conditions)
    $stmt = $pdo->prepare('SELECT COUNT(*) FROM XYZ_PROFILE WHERE Email = ?');
    $stmt->execute([$email]);
    if ($stmt->fetchColumn() > 0) {
        echo json_encode(['success' => false, 'message' => 'Email already exists']);
        exit();
    }
    
    // 2. Hash the password before storing (CRITICAL SECURITY STEP)
    $hashed_password = password_hash($password, PASSWORD_DEFAULT);

    // 3. Insert the new user into the database
    // NOTE: Add all other fields (DateOfBirth, Gender, Occupation, City, Mobile) 
    // to the SQL query and the execute array.
    try {
        $sql = "INSERT INTO XYZ_PROFILE (Email, Password, Name) VALUES (?, ?, ?)";
        $pdo->prepare($sql)->execute([$email, $hashed_password, $name]);
        
        $response['success'] = true;
    } catch (PDOException $e) {
        // Log the actual error
        $response['message'] = 'Database insertion failed.';
    }
}

echo json_encode($response);
?>