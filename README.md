# 3D Ray Tracing Engine

![Java](https://img.shields.io/badge/Language-Java-orange)
![Build](https://img.shields.io/badge/Build-Maven-blue)
![Course](https://img.shields.io/badge/Course-ISE5782-green)

**A high-performance rendering engine capable of creating photorealistic 3-dimensional scenes.**

Developed by **Raz & Avi** as a Mini Project for I.S.E (Introduction to Software Engineering), 2022.

---

## 🖼️ Sample Render
![Rendered Scene](https://user-images.githubusercontent.com/80701759/178141870-079d4e27-d6d9-416e-9771-6edcd87e76bd.png)

## 📖 Project Overview
This project is a pure Java implementation of a Ray Tracing Engine. It is designed to simulate physical light behavior to generate realistic images. The engine was built from the ground up emphasizing software engineering best practices, including **Agile methodologies** and **Test-Driven Development (TDD)**.

The architecture focuses on modularity and extensibility, allowing for easy addition of new geometric shapes, light sources, and rendering effects.

## ✨ Key Features

### Graphics & Rendering
* **Ray Tracing Core:** Simulates light paths to calculate shading and reflections.
* **Anti-Aliasing:** Super-sampling implementation to smooth jagged edges and improve image quality.
* **Depth of Field (DoF):** Simulates camera lens focus, blurring objects outside the focal plane.
* **Shadows:** implementation of both soft and hard shadows.

### Performance & Optimization
* **Bounding Volume Hierarchy (BVH):** Utilizes Axis-Aligned Bounding Boxes to significantly reduce intersection calculation time.
* **Multi-Threading:** Implements a custom **Thread Pool** to parallelize rendering tasks across CPU cores for faster image generation.

## 🏗️ Design & Architecture
The project strictly adheres to **Responsibility-Driven Design (RDD)** and utilizes several classic GoF Design Patterns:

* **Composite Pattern:** Used to manage complex hierarchies of geometric shapes and scenes.
* **Builder Pattern:** Facilitates the construction of complex objects (like Scenes or Cameras) step-by-step.
* **Wrapper (Decorator) Pattern:** Extends functionality of objects dynamically without altering their structure.



