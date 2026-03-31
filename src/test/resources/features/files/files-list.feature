Feature: Files List by Item ID

  Background:
    Given I am authenticated

  Scenario: List files when there are no files for the item

    When I GET "/api/v1/file/019ca71b-3183-7a8b-8f71-e44a327a7846"
    Then the response status code should be 204

  Scenario: List files when there is one file for the item

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

    When I GET "/api/v1/file/019ca71b-3183-7a8b-8f71-e44a327a7846"
    Then the response status code should be 200
    Then the response body should contain:
    """
    [
      {
        "id": "019ca71b-3183-7a8b-8f71-e44a327a7850",
        "fileName": "document.pdf",
        "urlS3": "https://bucket.s3.amazonaws.com/items/019ca71b-3183-7a8b-8f71-e44a327a7846/019ca71b-3183-7a8b-8f71-e44a327a7850_document.pdf",
        "size": 1024,
        "createdAt": "2026-03-31T10:00:00Z"
      }
    ]
    """

  Scenario: List files when there are multiple files for the item

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

    Given the following file exists in the database:
    """
    {
      "id": "019ca71b-3183-7a8b-8f71-e44a327a7851",
      "itemId": "019ca71b-3183-7a8b-8f71-e44a327a7846",
      "fileName": "image.jpg",
      "urlS3": "https://bucket.s3.amazonaws.com/items/019ca71b-3183-7a8b-8f71-e44a327a7846/019ca71b-3183-7a8b-8f71-e44a327a7851_image.jpg",
      "size": 2048,
      "createdAt": "2026-03-31T11:00:00Z"
    }
    """

    Given the following file exists in the database:
    """
    {
      "id": "019ca71b-3183-7a8b-8f71-e44a327a7852",
      "itemId": "019ca71b-3183-7a8b-8f71-e44a327a7846",
      "fileName": "spreadsheet.xlsx",
      "urlS3": "https://bucket.s3.amazonaws.com/items/019ca71b-3183-7a8b-8f71-e44a327a7846/019ca71b-3183-7a8b-8f71-e44a327a7852_spreadsheet.xlsx",
      "size": 4096,
      "createdAt": "2026-03-31T12:00:00Z"
    }
    """

    When I GET "/api/v1/file/019ca71b-3183-7a8b-8f71-e44a327a7846"
    Then the response status code should be 200
    Then the response body should contain:
    """
    [
      {
        "id": "019ca71b-3183-7a8b-8f71-e44a327a7850",
        "fileName": "document.pdf",
        "urlS3": "https://bucket.s3.amazonaws.com/items/019ca71b-3183-7a8b-8f71-e44a327a7846/019ca71b-3183-7a8b-8f71-e44a327a7850_document.pdf",
        "size": 1024,
        "createdAt": "2026-03-31T10:00:00Z"
      },
      {
        "id": "019ca71b-3183-7a8b-8f71-e44a327a7851",
        "fileName": "image.jpg",
        "urlS3": "https://bucket.s3.amazonaws.com/items/019ca71b-3183-7a8b-8f71-e44a327a7846/019ca71b-3183-7a8b-8f71-e44a327a7851_image.jpg",
        "size": 2048,
        "createdAt": "2026-03-31T11:00:00Z"
      },
      {
        "id": "019ca71b-3183-7a8b-8f71-e44a327a7852",
        "fileName": "spreadsheet.xlsx",
        "urlS3": "https://bucket.s3.amazonaws.com/items/019ca71b-3183-7a8b-8f71-e44a327a7846/019ca71b-3183-7a8b-8f71-e44a327a7852_spreadsheet.xlsx",
        "size": 4096,
        "createdAt": "2026-03-31T12:00:00Z"
      }
    ]
    """

