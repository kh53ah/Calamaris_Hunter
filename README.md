# Calamaris Hunter:

A lightweight, automated Java utility designed to monitor university cafeteria (Mensa) menus across multiple locations and notify the user when specific target dishes (e.g., calamari, schnitzel) are scheduled to be served.

---

## Overview:

Finding out whether your favorite meal is served at the university cafeteria often involves navigating through multiple individual menu pages. Calamaris Hunter automates this by fetching menu pages via standard HTTP requests, searching the retrieved content against a custom list of keywords, and presenting an automatic, self-closing desktop alert.

---

## Architecture & How It Works:

* Automation & Startup: Triggered automatically via the operating system scheduler (e.g., Windows Task Scheduler) upon user login on designated days (e.g., Monday mornings).
* Network Layer: Issues non-blocking HTTP GET requests using Java's built-in java.net.http.HttpClient with custom browser headers to prevent endpoint blocking.
* Multi-Target Processing: Loops through a mapped collection of cafeteria endpoints sequentially.
* Matching Engine: Normalizes the incoming page response and performs case-insensitive substring checks against an array of target keywords.
* Notification: Renders an auto-closing Swing GUI banner dialog for 15 seconds before terminating cleanly.

---

## Steps:

* HTTP Pipeline & Data Inspection [Done]: Implemented core HTTP GET requests using standard HttpClient and added utilities to extract and inspect the raw HTML response body.
* Multi-Canteen Mapping [Done]: Configured a structured Map<String, String> mapping university canteen names (e.g., Hubland Nord, Hubland Süd) to their respective menu endpoints.
* Keyword Matching Engine [Done]: Built a case-insensitive iteration loop checking page bodies against defined food keywords (e.g., "Calamari", "Kalamari", "Schnitzel").
* GUI Widget Alert: Integrate an auto-closing Swing JDialog / JWindow displaying matching canteen results and closing via a 15-second javax.swing.Timer.
* Weekly Schedule Expansion: Update request parameters or loop across Monday–Friday date tokens to inspect menus for all available days of the calendar week rather than the default "Heute" view.
* HTML Parsing Refinement: Optionally integrate JSoup to target specific menu containers (div, table) rather than searching the raw, full-page HTML markup.
* OS Automation: Register the packaged .jar into Windows Task Scheduler using javaw to run silently in the background on computer boot/login every Monday.