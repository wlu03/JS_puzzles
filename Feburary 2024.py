##Jane Street Feb 2024

import numpy as np
import matplotlib.pyplot as plt

def simulate_probability(sample_size):
    inside_circle = 0
    for run in range(sample_size):
        
        x1, y1 = np.random.rand(), np.random.rand()
        x2, y2 = np.random.rand(), np.random.rand()
        diameter = np.sqrt((x2 - x1)**2 + (y2 - y1)**2)

        x_center = (x1 + x2) / 2
        y_center = (y1 + y2) / 2

        distance_to_center = np.sqrt((x_center - 0.5)**2 + (y_center - 0.5)**2)
        if diameter / 2 > distance_to_center:
            inside_circle += 1

    probability = inside_circle / sample_size
    return probability

print(simulate_probability(10000000)) ##always around 0.5