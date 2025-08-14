# Storage Options Comparison Matrix

| Storage Option | Data Type                    | Storage Capacity Limits               | ACID Support | Backup Difficulty | Example from Task Management App                                                    |
| -------------- | ---------------------------- | ------------------------------------- | ------------ | ----------------- | ----------------------------------------------------------------------------------- |
| **Files**      | Unstructured                 | Limited by device storage (GBs-TBs)   | No           | Hard              | Store image attachments, PDF documents attached to tasks                            |
| **DataStore**  | Structured (key-value pairs) | Small datasets (MBs)                  | No           | Easy              | Store user preferences like dark mode, notification settings, last selected project |
| **Room**       | Structured (relational data) | Limited by SQLite (140TB theoretical) | Yes          | Medium            | Store Projects, Tasks, Users, and Attachments metadata with relationships           |

## Key Differences

- **Files**: Best for binary data like images, documents, media files
- **DataStore**: Ideal for simple configuration and user preferences
- **Room**: Perfect for complex relational data with transactions and queries

## ACID Properties

- **Atomicity**: Operations complete fully or not at all
- **Consistency**: Data remains valid after transactions
- **Isolation**: Concurrent operations don't interfere
- **Durability**: Committed changes persist even after system failure

Only Room (SQLite-based) provides full ACID compliance for our relational data needs.
