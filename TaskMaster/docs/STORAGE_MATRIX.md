# Storage Options Matrix

| Storage Option                        | Data Type                    | Storage Capacity Limits                                          | ACID Support | Backup Difficulty | Example from Task Management App                                                   |
| ------------------------------------- | ---------------------------- | ---------------------------------------------------------------- | ------------ | ----------------- | ---------------------------------------------------------------------------------- |
| **Room Database**                     | Structured                   | Limited by device storage and SQLite limits (~281TB theoretical) | Yes          | Medium            | Store Projects, Tasks, Users, and Attachments metadata with relationships          |
| **Files (Internal/External Storage)** | Unstructured                 | Limited by device storage space                                  | No           | Hard              | Store actual file attachments (images, PDFs, documents)                            |
| **DataStore (Preferences)**           | Key-value pairs (structured) | Small data only (~few MB recommended)                            | No           | Easy              | Store user preferences like dark mode, notification settings, default project view |

## Key Differences:

- **Room**: Best for complex relational data with transactions and queries
- **Files**: Best for binary data and large unstructured content
- **DataStore**: Best for simple key-value configuration data

## Usage in Task Management App:

1. **Room** handles all business logic data (users, projects, tasks, relationships)
2. **Files** store actual attachment content referenced by Room's Attachment entity
3. **DataStore** manages user preferences and app settings
