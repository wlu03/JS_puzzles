
import numpy as np
import matplotlib.pyplot as plt

# def simulate_probability(sample_size):
#     inside_circle = 0
#     for run in range(sample_size):
        
#         x1, y1 = np.random.rand(), np.random.rand()
#         x2, y2 = np.random.rand(), np.random.rand()
#         diameter = np.sqrt((x2 - x1)**2 + (y2 - y1)**2)

#         x_center = (x1 + x2) / 2
#         y_center = (y1 + y2) / 2

#         distance_to_center = np.sqrt((x_center - 0.5)**2 + (y_center - 0.5)**2)
#         if diameter / 2 > distance_to_center:
#             inside_circle += 1

#     probability = inside_circle / sample_size
#     return probability

# print(simulate_probability(10000000)) ##always around 0.5

def attempt2(sample_size):
    inside_circle = 0
    for x in range(sample_size):
        x1, y1 = np.random.rand(), np.random.rand()
        x2, y2 = np.random.rand(), np.random.rand()
        
        diameter = np.sqrt((x2 - x1)**2 + (y2 - y1)**2)
        radius = diameter/2
        
        x_mid = (x2 + x1)/2
        y_mid = (y2 + y1)/2
        
        #check all four corners
        if (x_mid + radius > 1 or x_mid - radius < 0 or y_mid + radius > 1 or y_mid - radius < 0):
            continue
        else:
            inside_circle += 1
    return inside_circle/sample_size

print(attempt2(10000000))


## Post Mortem
## Solution was incorrect, I had gotten pi/6 which was the probability that
## that the circle was inside the square. However, that was not the solution
## I needed to find the probability it was on the outside thus the correct answer
## was 1 - pi/6. I should also use real math instead of simulation.

