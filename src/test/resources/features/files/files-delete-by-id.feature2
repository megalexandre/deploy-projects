Feature: Files Delete by ID

  Background:
    Given I am authenticated

  Scenario: Delete file successfully

    Given the following file exists in the database:
    """
    {
      "id": "019ca71b-3183-7a8b-8f71-e44a327a7850",
      "itemId": "019ca71b-3183-7a8b-8f71-e44a327a7846",
      "fileName": "document.pdf",
      "urlS3": "https://bucket.s3.amazonaws.com/items/019ca71b-3183-7a8b-8f71-e44a327a7846/019ca71b-3183-7a8b-8f71-e44a327a7850_document.pdf",
      "size": 1024,
      "createdAt": "2026-03-31T10:00:00Z"
    }
    """

    Given the file "019ca71b-3183-7a8b-8f71-e44a327a7850_document.pdf" exists in S3

    When I DELETE "/api/v1/file/delete/019ca71b-3183-7a8b-8f71-e44a327a7850"
    Then the response status code should be 204
    Then the table "files" should have 0 records
    Then the file "items/019ca71b-3183-7a8b-8f71-e44a327a7846/019ca71b-3183-7a8b-8f71-e44a327a7850_document.pdf" should not exist in S3

  Scenario: Delete file that does not exist

    When I DELETE "/api/v1/file/delete/019ca71b-3183-7a8b-8f71-e44a327a9999"
    Then the response status code should be 404

  Scenario: Delete file and verify it cannot be downloaded afterwards

    Given the following file exists in the database:
    """
    {
      "id": "019ca71b-3183-7a8b-8f71-e44a327a7850",
      "itemId": "019ca71b-3183-7a8b-8f71-e44a327a7846",
      "fileName": "document.pdf",
      "urlS3": "https://bucket.s3.amazonaws.com/items/019ca71b-3183-7a8b-8f71-e44a327a7846/019ca71b-3183-7a8b-8f71-e44a327a7850_document.pdf",
      "size": 1024,
      "createdAt": "2026-03-31T10:00:00Z"
    }
    """

    Given the file "019ca71b-3183-7a8b-8f71-e44a327a7850_document.pdf" exists in S3

    When I DELETE "/api/v1/file/delete/019ca71b-3183-7a8b-8f71-e44a327a7850"
    Then the response status code should be 204

    When I GET "/api/v1/file/download/019ca71b-3183-7a8b-8f71-e44a327a7850"
    Then the response status code should be 404

  Scenario: Delete one file from multiple files of same item

    Given the following file exists in the database:
    """
    {
      "id": "019ca71b-3183-7a8b-8f71-e44a327a7850",
      "itemId": "019ca71b-3183-7a8b-8f71-e44a327a7846",
      "fileName": "document1.pdf",
      "urlS3": "https://bucket.s3.amazonaws.com/items/019ca71b-3183-7a8b-8f71-e44a327a7846/019ca71b-3183-7a8b-8f71-e44a327a7850_document1.pdf",
      "size": 1024,
      "createdAt": "2026-03-31T10:00:00Z"
    }
    """

    Given the following file exists in the database:
    """
    {
      "id": "019ca71b-3183-7a8b-8f71-e44a327a7851",
      "itemId": "019ca71b-3183-7a8b-8f71-e44a327a7846",
      "fileName": "document2.pdf",
      "urlS3": "https://bucket.s3.amazonaws.com/items/019ca71b-3183-7a8b-8f71-e44a327a7846/019ca71b-3183-7a8b-8f71-e44a327a7851_document2.pdf",
      "size": 2048,
      "createdAt": "2026-03-31T11:00:00Z"
    }
    """

    Given the file "019ca71b-3183-7a8b-8f71-e44a327a7850_document1.pdf" exists in S3
    Given the file "019ca71b-3183-7a8b-8f71-e44a327a7851_document2.pdf" exists in S3

    When I DELETE "/api/v1/file/delete/019ca71b-3183-7a8b-8f71-e44a327a7850"
    Then the response status code should be 204
    Then the table "files" should have 1 records

    When I GET "/api/v1/file/019ca71b-3183-7a8b-8f71-e44a327a7846"
    Then the response status code should be 200
    Then the response body should contain:
    """
    [
      {
        "id": "019ca71b-3183-7a8b-8f71-e44a327a7851",
        "fileName": "document2.pdf"
      }
    ]
    """

