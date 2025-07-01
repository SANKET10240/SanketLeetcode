from collections import defaultdict, deque

class Solution:
    def numBusesToDestination(self, routes, source, target):
        if source == target:
            return 0

        stop_to_routes = defaultdict(list)

 
        for i, route in enumerate(routes):
            for stop in route:
                stop_to_routes[stop].append(i)

        visited_routes = set()
        queue = deque()


        for route_idx in stop_to_routes[source]:
            queue.append(route_idx)
            visited_routes.add(route_idx)

        buses_taken = 1

        while queue:
            for _ in range(len(queue)):
                route_idx = queue.popleft()

                for stop in routes[route_idx]:
                    if stop == target:
                        return buses_taken

                    for neighbor_route in stop_to_routes[stop]:
                        if neighbor_route not in visited_routes:
                            visited_routes.add(neighbor_route)
                            queue.append(neighbor_route)

            buses_taken += 1

        return -1