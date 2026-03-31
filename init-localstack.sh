#!/bin/bash

# Script executado automaticamente quando LocalStack inicia
# Este script cria o bucket S3 necessário

set -e

echo "🚀 Configurando bucket S3 no LocalStack..."

BUCKET_NAME="deploy-board-uploads"
REGION="us-east-1"

# Aguardar S3 estar pronto
until awslocal s3 ls > /dev/null 2>&1; do
  echo "⏳ Aguardando S3 estar pronto..."
  sleep 2
done

# Criar bucket
echo "🪣 Criando bucket: $BUCKET_NAME"
awslocal s3 mb s3://$BUCKET_NAME --region $REGION 2>/dev/null || echo "✅ Bucket já existe"

# Configurar política de acesso público (apenas para desenvolvimento local)
echo "🔓 Configurando política de acesso..."
awslocal s3api put-bucket-acl --bucket $BUCKET_NAME --acl public-read 2>/dev/null || true

# Configurar CORS
echo "🔧 Configurando CORS..."
cat > /tmp/cors-config.json <<'EOF'
{
  "CORSRules": [
    {
      "AllowedOrigins": ["*"],
      "AllowedMethods": ["GET", "PUT", "POST", "DELETE", "HEAD"],
      "AllowedHeaders": ["*"],
      "ExposeHeaders": ["ETag", "Content-Length", "Content-Type"],
      "MaxAgeSeconds": 3600
    }
  ]
}
EOF

awslocal s3api put-bucket-cors \
    --bucket $BUCKET_NAME \
    --cors-configuration file:///tmp/cors-config.json 2>/dev/null || true

rm -f /tmp/cors-config.json

# Listar buckets criados
echo "📋 Buckets disponíveis:"
awslocal s3 ls

echo ""
echo "✅ Bucket S3 configurado com sucesso!"
echo "   Bucket: s3://$BUCKET_NAME"
echo "   Endpoint: http://localhost:4566"
echo "   Region: $REGION"
echo ""

