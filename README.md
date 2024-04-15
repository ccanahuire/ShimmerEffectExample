# Shimmer Loading Effect in Jetpack Compose

This repository demonstrates how to create a shimmer loading effect using Jetpack Compose. Shimmer loading effects are a common UI pattern used to indicate that a content is being loaded.

## Preview

![Shimmer Loading Effect Preview](preview.gif)

## How it Works

The shimmer loading effect is achieved by using a linear gradient `Brush` in Jetpack Compose. The logic behind the shimmer animation is just to create an infinite transition to the gradient offset position to create the shimmering effect. This transition gives the appearance of a shimmer as the gradient moves smoothly across the UI component.
