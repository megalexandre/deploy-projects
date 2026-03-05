# How to Fix Docker Permission Error

Your tests are failing because your user doesn't have permission to access Docker.

## 🚀 Quick Fix (Run Tests NOW)

```bash
# Fix the permission temporarily (resets on reboot)
sudo chmod 666 /var/run/docker.sock

# Now run your tests
./gradlew test --tests CucumberTest
```

## ✅ Permanent Fix (Recommended)

```bash
# 1. Add your user to the docker group
sudo usermod -aG docker $USER

# 2. Apply the changes (choose ONE):

# Option A: Run this in your current terminal
newgrp docker

# Option B: Log out and log back in

# Option C: Restart your computer

# 3. Verify it works
docker ps

# 4. Run your tests
./gradlew test --tests CucumberTest
```

## 📝 What Happened?

The error occurs because:
- Testcontainers needs to access Docker to create test containers
- Docker socket (`/var/run/docker.sock`) requires special permissions
- Your user `alex` is not in the `docker` group

## 🔍 Verify Fix

After applying the permanent fix, verify with:

```bash
# Check if you're in docker group
groups | grep docker

# Try Docker without sudo
docker run --rm hello-world
```

If these work without errors, your permission issue is fixed!

