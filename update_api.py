import os
import re
import glob

print("Starting frontend API string migrations...")

target_dir = r"d:\Project files\Student leave management system\leaveform"

# Files needing JS injection
html_files = glob.glob(os.path.join(target_dir, "*.html"))
js_files = glob.glob(os.path.join(target_dir, "*.js"))
all_files = html_files + js_files

script_tag = '<script src="config.js"></script>'

for file_path in all_files:
    if "config.js" in file_path:
        continue

    with open(file_path, "r", encoding="utf-8") as f:
        content = f.read()

    original = content
    
    # 1. Replace "http://localhost:8080/" inside templates or raw fetch
    # Using regex to target `"http://localhost:8080/` or `'http://localhost:8080/` or template literals
    content = re.sub(r'["\'`]http://localhost:8080/([^"\'`]*)["\'`]', r'`${CONFIG.API_BASE_URL}/\1`', content)
    
    # Target pure references without trailing slash
    content = re.sub(r'["\'`]http://localhost:8080["\'`]', r'CONFIG.API_BASE_URL', content)

    # 2. Inject config.js before closing </head> or <body> if HTML
    if file_path.endswith('.html') and script_tag not in content:
        if "</head>" in content:
            content = content.replace("</head>", f"  {script_tag}\n</head>")
        elif "<body>" in content:
            content = content.replace("<body>", f"<body>\n  {script_tag}")

    if content != original:
        with open(file_path, "w", encoding="utf-8") as f:
            f.write(content)
        print(f"Updated: {os.path.basename(file_path)}")

print("Migration complete!")
