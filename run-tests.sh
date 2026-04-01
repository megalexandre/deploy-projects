#!/bin/bash

# Script to run Gradle tests successfully
# This script checks prerequisites and runs the tests

set -e

echo "=========================================="
echo "🧪 Running Gradle Tests"
echo "=========================================="
echo ""

# Check if Docker is running
echo "1️⃣ Checking Docker..."
if ! docker ps >/dev/null 2>&1; then
    echo "❌ Docker is not accessible"
    echo ""
    echo "Please fix Docker permissions using ONE of these methods:"
    echo ""
    echo "QUICK FIX (temporary, resets on reboot):"
    echo "  sudo chmod 666 /var/run/docker.sock"
    echo ""
    echo "PERMANENT FIX (recommended):"
    echo "  sudo usermod -aG docker \$USER"
    echo "  newgrp docker"
    echo ""
    echo "Then run this script again."
    exit 1
fi

echo "✅ Docker is accessible"
echo ""

# Check if Java is available
echo "2️⃣ Checking Java..."
if ! command -v java >/dev/null 2>&1; then
    echo "❌ Java is not installed"
    exit 1
fi

JAVA_VERSION=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}' | awk -F '.' '{print $1}')
echo "✅ Java version: $(java -version 2>&1 | head -1)"
echo ""

# Pull PostgreSQL Docker image if not exists
echo "3️⃣ Checking PostgreSQL Docker image..."
if ! docker images | grep -q "postgres.*18-alpine"; then
    echo "📥 Pulling postgres:18-alpine image..."
    docker pull postgres:18-alpine
fi
echo "✅ PostgreSQL image ready"
echo ""

# Clean previous build
echo "4️⃣ Cleaning previous build..."
./gradlew clean --console=plain
echo "✅ Clean complete"
echo ""

# Run tests
echo "5️⃣ Running tests..."
echo "=========================================="
echo ""

./gradlew test --console=plain --info

echo ""
echo "=========================================="
echo "✅ Tests completed!"
echo "=========================================="
echo ""
echo "📊 View test report at:"
echo "  file://$(pwd)/build/reports/tests/test/index.html"
echo ""
echo "🥒 View Cucumber report at:"
echo "  file://$(pwd)/target/cucumber-report.html"
echo ""

