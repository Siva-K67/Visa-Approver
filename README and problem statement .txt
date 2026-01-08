README

Visa Rule Evaluation System (Core Java)
Overview

This project is a config-driven visa rule engine built using Core Java.
It decides whether a traveler needs a visa based on travel details and rules defined in a JSON file.

The main idea is to separate rules from code, so visa policies can be changed without modifying Java logic.

Input

The user provides:

Destination country

Passport country

Travel purpose

Stay duration (in days)

These inputs are combined into a TravelRequest object.

Rules

Visa rules are stored in a JSON file (visa-rules.json).

Each rule defines:

When it applies (country, purpose, stay duration)

What the decision should be (visa required or not)

Visa type

Warning messages

Rules are checked in order, and the first matching rule is applied.

Output

The system returns a VisaDecision containing:

Whether a visa is required

Type of visa

Any warning messages

Project Structure
src/
├── Main.java              // User input and output
├── model/                 // Enums and data objects
├── service/               // Visa evaluation logic
├── repository/            // Rule loading and storage
visa-rules.json            // Visa rules (config)

How It Works

User enters travel details via console

Rules are loaded from JSON

Rules are matched against the input

The first valid rule produces the decision

Result is printed to the console

Testing

Test scenarios are implemented using a simple test runner class.
They cover:

Valid rule match

No rule found

Multiple rule conflict

Invalid input

Missing rule fields

Tests create input programmatically (no user input).

Tech Used

Java 8+

Enums

Collections

Optional

JSON-based configuration


Summary

This project demonstrates how a rule-based system can be built in Core Java by keeping logic generic and rules external.


Problem Statement

⭐ CORE JAVA BRUSH-UP ASSIGNMENT (MANDATORY FOR ALL)

Assignment Title

Config-Driven Rule Evaluation System (Java)

This is a Java-only exercise. No frameworks, no Spring, no databases.

⸻

🎯 Objective

Build a config-driven rule engine that evaluates whether a traveler requires a visa for a given country.

This tests:
	•	Java fundamentals
	•	Clean OOP
	•	Enums
	•	Collections
	•	Defensive coding
	•	Real-world thinking

⸻

🧩 Functional Requirements

Inputs
	•	Country (enum)
	•	PassportCountry (enum)
	•	TravelPurpose (enum)
	•	StayDuration (int, days)

Output

A VisaDecision object containing:
	•	boolean visaRequired
	•	VisaType (enum)
	•	List<DocumentType>
	•	int estimatedProcessingDays
	•	List<String> warnings

⸻

📁 Data (Config-Based)

Rules must be loaded from JSON or YAML.


🛠️ Technical Constraints (Very Important)

Must Use
	•	Java 8+
	•	Enums (Country, VisaType, DocumentType)
	•	Immutable DTOs
	•	Java Collections
	•	Optional (where appropriate)

Must NOT Use
	•	Spring / Hibernate
	•	Databases
	•	Hardcoded logic per country
	•	Reflection hacks

⸻

🧠 Required Design Elements

Interns must implement:
	•	RuleLoader (reads config)
	•	RuleRepository (stores rules)
	•	VisaRuleEvaluator (core logic)
	•	VisaDecision (output object)

⸻

🧪 Test Scenarios (They Must Write These)
	•	Valid rule match
	•	No rule found
	•	Multiple rule conflict
	•	Invalid input
	•	Missing config fields