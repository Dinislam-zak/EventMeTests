<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <div class="row justify-content-center" style="margin-top: 50px;">
        <div class="col-md-6">
            <h2 class="text-center">User Profile</h2>
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">User Information</h5>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item"><strong>Username:</strong> ${user.username}</li>
                        <li class="list-group-item"><strong>Password:</strong> ${user.password}</li>
                        <li class="list-group-item"><strong>Email:</strong> ${user.email}</li>
                    </ul>
                </div>
            </div>

            <div class="text-center mt-3">
                <a href="/editProfile" class="btn btn-primary">Edit Profile</a>
                <a href="/logout" class="btn btn-danger">Logout</a>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>