import numpy as np

def find_equidistant_point(x1, y1, x2, y2):
    equidistant_found = False  # Flag to check if equidistant point exists
    
    # Calculate distances for each side
    distances = {
        "left": x2,
        "right": 1 - x2,
        "bottom": y2,
        "top": 1 - y2
    }
    
    # Determine the closest side
    closest_side = min(distances, key=distances.get)
    
    if closest_side == "left":
        y = (y1**2 - y2**2 + x1**2) / (2 * (y1 - y2)) if y1 != y2 else None
        if y is not None and 0 <= y <= 1:
            equidistant_found = True
            
    elif closest_side == "right":
        y = (y1**2 - y2**2 + (x1 - 1)**2) / (2 * (y1 - y2)) if y1 != y2 else None
        if y is not None and 0 <= y <= 1:
            equidistant_found = True
    
    elif closest_side == "bottom":
        x = (x1**2 - x2**2 + y1**2) / (2 * (x1 - x2)) if x1 != x2 else None
        if x is not None and 0 <= x <= 1:
            equidistant_found = True
    
    elif closest_side == "top":
        x = (x1**2 - x2**2 + (y1 - 1)**2) / (2 * (x1 - x2)) if x1 != x2 else None
        if x is not None and 0 <= x <= 1:
            equidistant_found = True
    
    return equidistant_found

# Simulation function with counters
def simulation(sample_size):
    equidistant_count = 0
    no_equidistant_count = 0
    
    for i in range(sample_size):
        x1, y1 = np.random.rand(), np.random.rand()  # red point
        x2, y2 = np.random.rand(), np.random.rand()  # blue point
        
        # Check for equidistant point
        if find_equidistant_point(x1, y1, x2, y2):
            equidistant_count += 1
        else:
            no_equidistant_count += 1
    
    print(f"Equidistant points found: {equidistant_count}")
    print(f"No equidistant points found: {no_equidistant_count}")

    total_points = equidistant_count + no_equidistant_count
    prob = equidistant_count / total_points
    print(f"The total probability from the simulation is: {prob}")

simulation(1000000000000)



