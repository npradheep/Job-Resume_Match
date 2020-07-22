import grpc
from concurrent import futures
import time

# import the generated classes
import conf_pb2
import conf_pb2_grpc

# import the original calculator.py
import MatchScore


# create a class to define the server functions, derived from
# calculator_pb2_grpc.CalculatorServicer
class MatchServicer(conf_pb2_grpc.getMatchRatioServicer):
    # calculator.square_root is exposed here
    # the request and response are of the data type
    # calculator_pb2.Number
    def getratio(self, request, context):
        res = MatchScore.getratio(request.jd, request.url)
        return conf_pb2.ResumeResponse(message=res)


# create a gRPC server
server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))

# use the generated function `add_CalculatorServicer_to_server`
# to add the defined class to the server
conf_pb2_grpc.add_getMatchRatioServicer_to_server(
        MatchServicer(), server)

# listen on port 50051
print('Starting server. Listening on port 50051.')
server.add_insecure_port('[::]:50051')
server.start()

# since server.start() will not block,
# a sleep-loop is added to keep alive
try:
    while True:
        time.sleep(86400)
except KeyboardInterrupt:
    server.stop(0)